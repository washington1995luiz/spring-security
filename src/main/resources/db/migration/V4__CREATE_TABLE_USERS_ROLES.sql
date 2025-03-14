CREATE TABLE IF NOT EXISTS tb_users_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES tb_roles(id) ON DELETE CASCADE,
    PRIMARY KEY(user_id, role_id)
);