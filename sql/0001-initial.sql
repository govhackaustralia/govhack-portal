-- noinspection SqlNoDataSourceInspectionForFile
CREATE TABLE competition (
  id       UUID PRIMARY KEY,
  created  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated  TIMESTAMP WITH TIME ZONE,
  accessed TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  name     TEXT NOT NULL
);
CREATE TRIGGER update_competition_changetimestamp
  BEFORE UPDATE
  ON competition
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE team (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  competition_id UUID REFERENCES competition (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_team_changetimestamp
  BEFORE UPDATE
  ON team
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE govhack_user (
  id         UUID PRIMARY KEY,
  created    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated    TIMESTAMP WITH TIME ZONE,
  accessed   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  username   TEXT NOT NULL,
  password   TEXT,
  email      TEXT,
  first_name TEXT,
  last_name  TEXT,
  roles      TEXT []                  DEFAULT '{}',
  team_id    UUID REFERENCES team (id)
);
CREATE TRIGGER update_user_changetimestamp
  BEFORE UPDATE
  ON govhack_user
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE sponsor (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  competition_id UUID REFERENCES competition (id),
  owner_id       UUID REFERENCES govhack_user (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_sponsor_changetimestamp
  BEFORE UPDATE
  ON sponsor
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE region (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  competition_id UUID REFERENCES competition (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_region_changetimestamp
  BEFORE UPDATE
  ON region
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE event (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  competition_id UUID REFERENCES competition (id),
  owner_id       UUID REFERENCES govhack_user (id),
  region_id      UUID REFERENCES region (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_event_changetimestamp
  BEFORE UPDATE
  ON event
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE prize (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  competition_id UUID REFERENCES competition (id),
  sponsor_id     UUID REFERENCES sponsor (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_prize_changetimestamp
  BEFORE UPDATE
  ON prize
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE entry (
  id             UUID PRIMARY KEY,
  created        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated        TIMESTAMP WITH TIME ZONE,
  accessed       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  team_id        UUID REFERENCES team (id),
  competition_id UUID REFERENCES competition (id),
  name           TEXT NOT NULL
);
CREATE TRIGGER update_entry_changetimestamp
  BEFORE UPDATE
  ON entry
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();

CREATE TABLE entries_prizes (
  id       UUID PRIMARY KEY,
  created  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated  TIMESTAMP WITH TIME ZONE,
  entry_id UUID REFERENCES entry (id),
  prize_id UUID REFERENCES prize (id)
);
CREATE TRIGGER update_entries_prizes_changetimestamp
  BEFORE UPDATE
  ON entries_prizes
  FOR EACH ROW EXECUTE PROCEDURE updated_trigger_changetimestamp();