CREATE DATABASE caso_libros;
CREATE USER usr_caso_libros WITH ENCRYPTED PASSWORD 'usr';
GRANT ALL PRIVILEGES ON DATABASE caso_libros TO usr_caso_libros;