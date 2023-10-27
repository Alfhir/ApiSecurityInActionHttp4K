CREATE USER dungeon_api_user PASSWORD 'password';
GRANT SELECT, INSERT ON dungeon, room, monster, TO dungeon_api_user;
GRANT DELETE ON monster TO dungeon_api_user;