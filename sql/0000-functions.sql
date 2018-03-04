--liquibase formatted sql

--changeset tim:1
CREATE OR REPLACE FUNCTION updated_trigger_changetimestamp()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.updated = now();
  RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';
-- END_LIQUIBASE
