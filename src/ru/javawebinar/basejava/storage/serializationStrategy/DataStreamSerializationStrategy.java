package ru.javawebinar.basejava.storage.serializationStrategy;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializationStrategy implements SerializationStrategy {

    @Override
    public void writeResume(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeMap(dos, resume.getContacts(), (contactType, value) -> {
                dos.writeUTF(contactType.name());
                dos.writeUTF(value);
            });

            writeMap(dos, resume.getSections(), (sectionType, section) -> {
                dos.writeUTF(sectionType.name());
                writeSectionContent(dos, sectionType, section);
            });
        }
    }

    @Override
    public Resume readResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            readEntries(dis, () -> {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                resume.addContact(contactType, dis.readUTF());
            });

            readEntries(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSectionContent(dis, sectionType));
            });

            return resume;
        }
    }

    private <K, V> void writeMap(DataOutputStream dos, Map<K, V> map, EntryWriter<K, V> writer) throws IOException {
        dos.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            writer.write(entry.getKey(), entry.getValue());
        }
    }

    private void readEntries(DataInputStream dis, EntryProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private void writeSectionContent(DataOutputStream dos, SectionType sectionType, Section section) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
            case ACHIEVEMENTS, QUALIFICATIONS -> writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
            case EXPERIENCE, EDUCATION -> writeCollection(dos, ((OrganizationSection) section).getOrganization(), org -> {
                dos.writeUTF(org.getHomePage().getName());
                dos.writeUTF(Optional.ofNullable(org.getHomePage().getUrl()).orElse(""));
                writeCollection(dos, org.getPositions(), position -> {
                    writeLocalDate(dos, position.getStartDate());
                    writeLocalDate(dos, position.getEndDate());
                    dos.writeUTF(position.getTitle());
                    dos.writeUTF(Optional.ofNullable(position.getDescription()).orElse(""));
                });
            });
        }
    }

    private Section readSectionContent(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENTS, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(readList(dis, () -> new Organization(
                    new Link(dis.readUTF(), dis.readUTF()),
                    readList(dis, () -> new Position(
                            readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                    ))
            )));
        };
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    @FunctionalInterface
    private interface EntryWriter<K, V> {
        void write(K key, V value) throws IOException;
    }

    @FunctionalInterface
    private interface EntryProcessor {
        void process() throws IOException;
    }

    @FunctionalInterface
    private interface ElementReader<T> {
        T read() throws IOException;
    }

    @FunctionalInterface
    private interface ElementWriter<T> {
        void write(T element) throws IOException;
    }
}
