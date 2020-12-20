# Eksamensprojekt
Eksamensprojekt 2020 KEA
Af JMB, JL, RM, RT

1. For at kunne køre projektet har vi vedlagt et script der opretter en database med data. Dette er både vedlagt på WiseFlow og her på Gitub (https://github.com/RasmusKEA/Eksamensprojekt/blob/main/DBcreate_Gruppe_1.sql)
2. For at bruge dette script, skal der oprettes en database i MySQL workbench, vi har kaldt den alphasolutions. 
3. Dernest tilgås denne, der trykkes på "Server" -> Data Import -> Import from self-contained file -> Import progress -> Start import

Derudover skal man også tilføje en mysql connector driver til root-mappen i intellij for at kunne køre programmet.
1. Denne fil ligger på Fronter -> Rum -> dat20v1 -> Ressourcer -> Semester 2 -> Programmering -> Mysql -> MySqlJDBCDriver -> mysql-connector-java-8.0.21.jar
2. Efter denne er downloadet åbnes projektet i IntelliJ, der højreklikkes på "Eksamensprojekt"-mappen -> Open module settings -> Libraries -> "+" -> Java -> Find der hvor din downloadede .jar fil er -> Open -> Apply -> OK
3. Til sidst skal "user" og "password" i DBConnection klassen ændres til dit eget MySQL workbench login. 


Der er ydermere oprettet et login til alle vejledere i databasen. Dette login er:
Email: jeres kea mail.
Password: fornavn i lower case.
