/* Создание таблицы отражающей дружбу пользователей */
create table if not exists users_friends (
                               user_id varchar(36) not null references users(id) on DELETE cascade,
                               friends_id varchar(36) not null references users(id) on DELETE cascade,
                               primary key (user_id, friends_id)
)