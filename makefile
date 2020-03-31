MIGRATION_LABEL = "to-be-changed"
DATE_WITH_TIME := $(shell /bin/date "+%Y%m%d%H%M%S")

makeMigration:
	mvn liquibase:diff -DdiffChangeLogFile=src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.yml
	@echo "  - include:" >> src/main/resources/db/changelog/db.changelog-master.yml
	@echo "      file: classpath*:db/changelog/changes/$(DATE_WITH_TIME)-$(MIGRATION_LABEL).yml" >> src/main/resources/db/changelog/db.changelog-master.yml