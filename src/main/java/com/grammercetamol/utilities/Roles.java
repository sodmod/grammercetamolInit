package com.grammercetamol.utilities;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.grammercetamol.utilities.Permissions.*;

public enum Roles {
    STUDENTS(Sets.newHashSet(STUDENT_WRITE, STUDENT_READ)),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> permissions() {
        return permissions;
    }
}
