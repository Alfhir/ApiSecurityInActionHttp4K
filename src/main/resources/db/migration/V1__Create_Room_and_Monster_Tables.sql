-- Create the dungeon table
CREATE TABLE IF NOT EXISTS dungeon (
                                       dungeon_id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       description TEXT
);

-- Create the meter type
DO $$ BEGIN
    CREATE TYPE meter AS (value NUMERIC(10, 2));
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

-- Create the 'room' table
CREATE TABLE IF NOT EXISTS room
(
    room_id SERIAL PRIMARY KEY,
    dungeon_id INT REFERENCES dungeon (dungeon_id),
    dimensions meter[] NOT NULL,
    sensory_impression TEXT,
    initial_observations TEXT,
    findings TEXT,
    gm_information TEXT
);

-- Create the 'monster' table
CREATE TABLE IF NOT EXISTS monster
(
    monster_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    description JSONB
);

-- Create the 'room_monster' table
CREATE TABLE IF NOT EXISTS room_monster
(
    room_monster_id SERIAL PRIMARY KEY,
    room_id INT REFERENCES room (room_id),
    monster_id INT REFERENCES monster (monster_id) UNIQUE,
    reasons_for_being_here TEXT
);