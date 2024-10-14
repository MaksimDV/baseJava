package ru.javawebinar.basejava.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Organization> organization;

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organization) {
        Objects.requireNonNull(organization, "Organization must not be NULL");
        this.organization = organization;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        return organization.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organization.equals(that.organization);
    }

    @Override
    public int hashCode() {
        return organization.hashCode();
    }
}
