#lang racket

(require db)

(define us2004_companies_scheme "create table if not exists us2004_companies(
id int(11) primary key auto_increment,
companyname varchar(128) not null default '',
bod varchar(255) not null default '',
url varchar(255) not null default '',
objects mediumtext not null,
bod_url varchar(255) not null default '',
symbol varchar(50) not null default ''
)")

(define us2004_companies_insert_scheme "insert into us2004_companies (companyname, bod, url, objects, bod_url , symbol)
values('~a', '~a', '~a', '~a', '~a', '~a')")

(define us2004_directors_scheme "create table if not exists us2004_directors (
id int(11) primary key auto_increment,
firstname varchar(128) not null default '',
lastname varchar(128) not null default '',
gender char(1) not null default 'm',
boards varchar(255) not null default '',
url varchar(255) not null default '',
institutions varchar(255) not null default ''
)")

(define host "10.204.43.88")
(define port 3306)
(define user "root")
(define password "root")
(define database "demo")

(define conn
  (mysql-connect
   #:server host
   #:port port
   #:database database
   #:user user
   #:password password
   )
  )

(list-tables conn)

;(query-exec conn "drop table us2004_companies")
;(query-exec conn "drop table us2004_directors")

(query-exec conn us2004_companies_scheme)
(query-exec conn us2004_directors_scheme)

(list-tables conn)

(define (batch-data-for-companies num)
  (for/list ([n num])
      (define ns (string-append "_" (number->string n)))

      (define companyname (string-append "companyName" ns))
      (define bod (string-append "bod" ns))
      (define url (string-append "url" ns))
      (define objects (string-append "objects" ns))
      (define bod_url (string-append "bod_url" ns))
      (define symbol (string-append "symbol" ns))
      (query-exec conn (format us2004_companies_insert_scheme companyname bod url objects bod_url symbol)) 
    )
  )

(batch-data-for-companies 10000)
(query-rows conn "select * from us2004_companies")

