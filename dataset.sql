-- start by connecting to postgres default db to create pl_stats
psql -U postgres

CREATE DATABASE pl_stats;

GRANT ALL PRIVILEGES ON DATABASE "pl_stats" TO postgres;

-- switch to pl_stats database
\c pl_stats

-- create the table in pl_stats
CREATE TABLE player_data (
    player_name VARCHAR(100),
    nation VARCHAR(50),
    position VARCHAR(10),
    age INTEGER,
    matches_played INTEGER,
    starts INTEGER,
    minutes_played FLOAT,
    goals FLOAT,
    assists FLOAT,
    penalties_scored FLOAT,
    yellow_cards FLOAT,
    red_cards FLOAT,
    expected_goals FLOAT,
    expected_assists FLOAT,
    team_name VARCHAR(100)
);

-- confirm table is empty
SELECT * FROM player_data;

-- copy csv data into the table (ensure file path is correct)
\COPY player_data FROM 'destination_to_file' DELIMITER ',' CSV HEADER;

-- confirm data was loaded
SELECT * FROM player_data;
