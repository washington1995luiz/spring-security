CREATE TABLE IF NOT EXISTS tb_roles_permissions (
    role_id UUID NOT NULL,
    permission_id UUID NOT NULL,
    FOREIGN KEY (role_id) REFERENCES tb_roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES tb_permissions(id) ON DELETE CASCADE,
    PRIMARY KEY(role_id, permission_id)
);