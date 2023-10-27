-- Drop the monster_room table if it exists (unique idx was on the wrong column anyways)
DROP TABLE IF EXISTS monster_room;

-- Drop the dungeon table if it exists
DROP TABLE IF EXISTS dungeon;

-- Create the dungeon table
CREATE TABLE IF NOT EXISTS dungeon (
                                       dungeon_id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       description TEXT
);

-- Drop the room table if it exists
DROP TABLE IF EXISTS room;

-- Create the 'room' table
CREATE TABLE IF NOT EXISTS room (
                                    room_id SERIAL PRIMARY KEY,
                                    dungeon_id INT REFERENCES dungeon (dungeon_id),
                                    dimensions meter[] NOT NULL,
                                    sensory_impression TEXT,
                                    initial_observations TEXT,
                                    findings TEXT,
                                    gm_information TEXT,
                                    monsters INT[] -- An array of monster IDs in the room
);

-- Drop the monster table if it exists
DROP TABLE IF EXISTS monster;

-- Create the 'monster' table
CREATE TABLE IF NOT EXISTS monster (
                                       monster_id SERIAL PRIMARY KEY,
                                       name VARCHAR(100),
                                       description JSONB
);
