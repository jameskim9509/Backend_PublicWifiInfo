# BackendSchool_PublicWifiInfo
  get PublicWifiInfo by seoul_openAPI and manage data

implement MVC pattern in servlet coding. <br>
( front controller getting url and searching for right handler ->
                   get view returned by handler and pass parameters to right jsp file )

getting PublicWifiInfo (by single thread) from seoul_openAPI portal, web server throws wait page. <br>
After waiting, data is stored to sqlite database

with database crud functions and joining, webserver can display appropriate page where people want to see <br>
(like bookmark and bookmarkgroup, wifi information around me, etc)

Also with adequate css, jsp, javascript, html coding, webserver can display clean and readable web page 

ERD 파일
<img src = "./참고 자료/ERD 캡처">
