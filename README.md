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

주요 기능
1. 서울 공공 와이파이 api를 통해 와이파이 정보들을 얻어와 데이터베이스에 저장
2. 내 위치 정보를 얻어오고, 가까운 위치(거리) 순서로 웹 페이지에 20개의 와이파이 정보를 표시
3. 북마크 그룹을 생성해 원하는 와이파이 정보를 북마크로 관리(추가, 수정, 삭제)
4. 내 위치 정보를 조회한 시간별로 저장

소스 구조                                                                              
src                                                   
  └─ main                                               
     ├─ java                                            
     │  ├─ mvc                                          
     │  │  ├─ Adapter.java                              
     │  │  ├─ AddBookmarkGroupController.java           
     │  │  ├─ AddBookmarkGroupSubmitController.java     
     │  │  ├─ AddBookmarkSubmitController.java          
     │  │  ├─ Controller.java                           
     │  │  ├─ ControllerAdapter.java                    
     │  │  ├─ DeleteBookmarkController.java             
     │  │  ├─ DeleteBookmarkGroupController.java        
     │  │  ├─ DeleteBookmarkGroupSubmitController.java  
     │  │  ├─ DeleteBookmarkSubmitController.java       
     │  │  ├─ DeleteHistorySubmitController.java        
     │  │  ├─ DetailController.java                     
     │  │  ├─ EditBookmarkGroupController.java          
     │  │  ├─ EditBookmarkGroupSubmitController.java    
     │  │  ├─ FrontController.java                      
     │  │  ├─ HistoryController.java                    
     │  │  ├─ HomeController.java                       
     │  │  ├─ LoadWifiController.java                   
     │  │  ├─ ManageBookmarkGroupController.java        
     │  │  ├─ ModelView.java                            
     │  │  ├─ ShowBookmarkListController.java           
     │  │  └─ View.java                                 
     │  ├─ repository                                   
     │  │  ├─ Bookmark_Repository.java                  
     │  │  ├─ History_Repository.java                   
     │  │  └─ Public_Wifi_Info_Repository.java          
     │  └─ thread                                       
     │     ├─ LoadWifiInfoThread.java                   
     │     └─ LoadWifiThreadManager.java                
     └─ webapp                                          
        ├─ META-INF                                     
        │  └─ MANIFEST.MF                               
        ├─ WEB-INF                                      
        │  ├─ lib                                       
        │  ├─ add-bookmark-submit.jsp                   
        │  ├─ add-bookmarkGroup-submit.jsp              
        │  ├─ add-bookmarkGroup.jsp                     
        │  ├─ delete-bookmark-submit.jsp                
        │  ├─ delete-bookmark.jsp                       
        │  ├─ delete-bookmarkGroup-submit.jsp           
        │  ├─ delete-bookmarkGroup.jsp                  
        │  ├─ delete-history-submit.jsp                 
        │  ├─ detail.jsp                                
        │  ├─ edit-bookmarkGroup-submit.jsp             
        │  ├─ edit-bookmarkGroup.jsp                    
        │  ├─ history.jsp                               
        │  ├─ home.jsp                                  
        │  ├─ load-wifi.jsp                             
        │  ├─ manage-bookmarkGroup.jsp                  
        │  ├─ show-bookmarkList.jsp                     
        │  └─ wait.jsp                                  
        └─ style.css                                    
<br>                                                                     

ERD 파일 <br>
(하나의 북마크는 하나의 와이파이 정보를 반드시 가지지만, 와이파이 정보는 여러 북마크로 지정될 수도 있고 지정되지 않을 수도 잇다.) <br><br>
<img width="800px" height="500px" src = "./ERD_capture.PNG">
