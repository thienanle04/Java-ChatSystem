use chatsystem;

insert into chat_group (group_name, created_by) value ('Java chat app', 1);

insert into group_members (group_id, user_id) values (1, 1), (1, 2), (1, 4);