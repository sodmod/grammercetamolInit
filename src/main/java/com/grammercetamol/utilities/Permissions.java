package com.grammercetamol.utilities;

public enum Permissions {
    STUDENT_READ("student:read"),
    ADMIN_READ("admin:read"),
    STUDENT_WRITE("student:write"),
    ADMIN_WRITE("admin:write");

    private final String permissions;

    Permissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions(){
        return permissions;
    }
}
