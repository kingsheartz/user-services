

//Get URL params

function getURLParams(key) {
    let urlString = window.location.href;
    //alert(urlString)
    let paramString = urlString.split('?')[1];
    let params_arr = paramString.split('&');
    let pair;
    for (let i = 0; i < params_arr.length; i++) {
        pair = params_arr[i].split('=');
        if (pair[0].trim() == key) {
            console.log("Key is:", pair[0]);
            console.log("Value is:", pair[1]);
            $('#username').html(pair[1].trim());
            return;
        }

    }

}

//Setting up of name in welcome page
function loaded() {

    getURLParams("user");


}




//===========================================================================================
//                       L O G   I N
//===========================================================================================


function loginValidate() {

    //Getting form values
    var username = $('#username').val();
    var pwd = $('#pwd').val();



    //Creating object of Login
    var data = {};

    //Set values
    data._username = username;
    data._password = pwd;

    //Stringify Data
    data = JSON.stringify(data);

    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/login",
        data: data,
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert("Log in Successfull");
                $("#login").addClass("hidden")
                location.href = "welcomePage.html?user=" + data._responseObject._firstName;
                //return;
            }
            else if (data._responseCode == 202) {
                alert("Invalid credential");
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });

}

//===========================================================================================
//                         L O G   I N
//===========================================================================================





//===========================================================================================
//               R E G I S T E R 
//===========================================================================================

function register() {
    $('.hidden').removeClass("active");
    $('#register').addClass("active");
}

function registerUser() {

    //Getting form values
    var username = $('#regFirstName').val();
    var email = $('#regEmail').val();
    var phone = $('#regPhone').val();
    var pin = $('#regPin').val();
    var address = $('#regAddress').val();
    var pwd = $('#regPassword').val();

    //alert(username + pwd)

    //Creating object of Login
    var data = {};

    //Set values
    data._firstName = username;
    data._email = email;
    data._phone = phone;
    data._pin = pin;
    data._address = address;
    data._password = pwd;


    //Stringify Data
    data = JSON.stringify(data);

    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/create",
        data: data,
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert(data._responseMessage);
                location.href = "welcomePage.html?user=" + data._responseObject._firstName;
                //return;
            }
            else if (data._responseCode == 202) {
                alert("Registration error occured");
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });

}

//===========================================================================================
//              R E G I S T E R 
//===========================================================================================




//===========================================================================================
//              U P D A T E    P H O N E
//===========================================================================================

function updatePhone() {
    $('.hidden').removeClass("active");
    $('#update').addClass("active");

}

function updatePhoneService() {

    //Getting form values
    var userId = $('#updateUserId').val();
    var phone = $('#updateNumber').val();



    //Creating object of Login
    var data = {};

    //Set values
    data._phone = phone;

    //Stringify Data
    data = JSON.stringify(data);

    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/update?id=" + userId,
        data: data,
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert("Update phone Successfully");
                location.href = "welcomePage.html?user=" + data._responseObject._firstName;
                //return;
            }
            else if (data._responseCode == 202) {
                alert(`Phone number isn't Updated , ${data._responseMessage}`);
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });


}


//===========================================================================================
//              U P D A T E    P H O N E
//===========================================================================================


//===========================================================================================
//              R E M O V E   U S E R
//===========================================================================================

function removeUser() {
    $('.hidden').removeClass("active");
    $('#removeUser').addClass("active");
}

function removeUserService() {

    //Getting form values
    var userId = $('#removeUserId').val();




    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/remove?id=" + userId,
        data: "",
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert("User removed Successfully");
                location.href = "welcomePage.html?user=" + data._responseObject._firstName;
                //return;
            }
            else if (data._responseCode == 202) {
                alert(data._responseMessage);
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });

}

//===========================================================================================
//              R E M O V E   U S E R
//===========================================================================================


//===========================================================================================
//              D E L E T E   U S E R
//===========================================================================================

function deleteUser() {
    $('.hidden').removeClass("active");
    $('#deleteUser').addClass("active");
}

function deleteUserService() {

    //Getting form values
    var userId = $('#deleteUserId').val();




    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/delete?id=" + userId,
        data: "",
        type: "delete",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert("User deleted Successfully");
                location.href = "welcomePage.html?user=" + data._responseObject._firstName;
                //return;
            }
            else if (data._responseCode == 202) {
                alert(data._responseMessage);
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });
}

//===========================================================================================
//              D E L E T E   U S E R
//===========================================================================================



//===========================================================================================
//                S E L E C T   U S E R
//===========================================================================================

function selectUser() {
    $('.hidden').removeClass("active");
    $('#selectUser').addClass("active");
}

function selectUserService() {

    //Getting form values
    var userId = $('#selectUserId').val();




    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/select?id=" + userId,
        data: "",
        type: "get",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {
                alert("User Founded");
                $("#selectUserFirstName").html(data._responseObject._firstName);
                $("#selectUserPhone").html(data._responseObject._phone);
                $("#selectUserEmail").html(data._responseObject._email);
                $("#selectUserPin").html(data._responseObject._pin);
                $("#selectUserAddress").html(data._responseObject._address);

                $('#userTable').addClass("active");

                //return;
            }
            else if (data._responseCode == 202) {
                alert(data._responseMessage);
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });
}

//===========================================================================================
//                S E L E C T   U S E R
//===========================================================================================



//===========================================================================================
//                S E L E C T   U S E R   L I S T
//===========================================================================================

function selectUserList() {




    userDataList


    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/selectusers",
        data: "",
        type: "get",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data._responseCode == 200) {

                console.log(data._responseList)
                var userDataList = `<tr><th colspan="4" style="text-align:center;"><u>U S E R  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
                                    <tr>
                                        <th>Name</th>
                                        <th>Phone</th>
                                        <th>Email</th>
                                        <th>Pin</th>
                                    </tr>`

                data._responseList.forEach(user => {

                    userDataList += `
                                    <tr>
                                        <td>${user._firstName}</td>
                                        <td>${user._phone}</td>
                                        <td>${user._email}</td>
                                        <td>${user._pin}</td>
                                    </tr>
                                    `
                });
                $("#userDataList").html(userDataList);

                return;
            }
            else if (data._responseCode == 202) {
                alert(data._responseMessage);
                return;
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });





    $('.hidden').removeClass("active");
    $('#selectUserList').addClass("active");

}



//===========================================================================================
//                S E L E C T   U S E R    L I S T
//===========================================================================================


//===========================================================================================
//               S E N D   L O G    D A T A
//===========================================================================================

function sendLogData() {

    $('.hidden').removeClass("active");
    $('#sendLogData').addClass("active");
}
function sendLogDataService() {

    //Getting form values
    var name = $('#logDataName').val();
    var email = $('#logDataEmail').val();

    //Creating object
    var data = {};
     if(name == "" || email == ""){
         alert("Input fields can't be empty");
         return;
     }
    //Set values
    data.name = name;
    data.email = email;


    //Stringify Data
    data = JSON.stringify(data);

    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/sendindex",
        data: data,
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {
            if (data) {

                console.log(data)

                if (data._responseCode == 201) {
                    alert(data._responseMessage);
                }
                else {
                    alert("Error occured , please try again later");
                }
            }
            else {
                alert("Error occured , please try again later");
            }
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });

}



//===========================================================================================
//                S E N D   L O G    D A T A
//===========================================================================================


//===========================================================================================
//               G E T   L O G    D A T A
//===========================================================================================

var indices=[];
var keys=null;
function recieveLogData() {

    $('.hidden').removeClass("active");
    $('#getLogData').addClass("active");


    //Ajax Call
    $.ajax({
        url: "http://localhost:8080/service-1.0/rest/services/getindices",
        data: "",
        type: "get",
        timeout: 18000,
        success: function (data) {
            console.log(data)
            
            data.forEach(function(index,i) {
                console.log(i,index.index);
                //push index to indices array
                indices.push(index.index);
            });
        }
    })
}

function fetchSearchElement(){

    var indexName = $('#getDataIndexName').val();
    keys=null;

    if(indices.includes(indexName)){
      
        //data.indexName=indexName;
        url = "http://localhost:8080/service-1.0/rest/services/searchelements";

        //Ajax Call
        $.ajax({
            url: url,
            data: indexName,
            type: "post",
            contentType: 'application/json',
            dataType: "json",
            timeout: 18000,
            success: function (data) {

                console.log(data.hits)
                //if there is any response data
                if (data.hits.hits.length > 0) {
                    //Getting keys from Object (_source) to Array (keys)
                    keys=Object.keys(data.hits.hits[0]._source);
                    
                    userDataList = `<tr><th colspan="4" style="text-align:center;"><u>L O G  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
                        <tr>
                            <th>`+keys[0]+`</th>
                            <th>`+keys[1]+`</th>
                        </tr>`
    
                    data.hits.hits.forEach(log => {
                        //console.log(keys)
                        userDataList += `
                            <tr>
                                <td>${log._source[keys[0]]}</td>
                                <td>${log._source[keys[1]]}</td>
                            </tr>
                            `
                    });

                    var searchFields=`
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">@</span>
                            <input type="text" id="get`+keys[1]+`" autocomplete="false" class="form-control"
                                placeholder="`+keys[1]+`" aria-label="get`+keys[1]+`" aria-describedby="basic-addon1">
                        </div>`;
                    
                    $("#searchFields").html(searchFields);
                    $("#logDataReciever").html(userDataList);
                }
            }
        })
    }
    else{
        $('#searchFields').html("");
        $('logDataReciever').html("");
    }
}

function recieveLogDataService() {
    console.log(indices + " "+keys);
    //Getting form values
    var indexName = $('#getDataIndexName').val();
    
    var from = $('#getDataFrom').val();
    var size = $('#getDataSize').val();
    var url = null;
    var indexName = $('#getDataIndexName').val();

    if(indices.includes(indexName)){
        
        var key1=$('#get'+keys[1]+'').val();
    }


    if (from === "" || isNaN(Number(from))) {
        console.log("case 1 - from")
        from = 0;
    }
    if (size === "" || isNaN(Number(size))) {
        console.log("case 1 - size")
        url = "http://localhost:9200/" + indexName + "/_search?from=" + from + "";
    }
    else {
        console.log("case 2 - size")
        url = "http://localhost:9200/" + indexName + "/_search?from=" + from + "&size=" + size + "";
    }

    //Creating object
    var data = {};
    var query = {};
    var match_phrase = {};
    //Set values
    if(key1&&key1!=""){
        console.log("case 1");
        match_phrase[keys[1]]=(keys[1]=="logmessage")?key1+"\r" : key1;
        query.match_phrase = match_phrase;
        console.log(match_phrase)
    }
    else{
        console.log("case 2");
        query.match_all={}
    }

    
    data.query = query;

    console.log(data , url)
    //Stringify Data
    data = JSON.stringify(data);
    

    //Ajax Call
    $.ajax({
        url: url,
        data: data,
        //data:"",
        type: "post",
        contentType: 'application/json',
        dataType: "json",
        timeout: 18000,
        success: function (data) {

            console.log(data)
            //Table data
            var userDataList = null;
            //If any data present
            if (data.hits.hits.length > 0) {
                if (data.hits.hits[0]._source.logmessage) {

                    userDataList = `<tr><th colspan="4" style="text-align:center;"><u>L O G  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
                    <tr>
                        <th>Log message</th>
                        <th>Response</th>
                    </tr>`

                data.hits.hits.forEach(log => {

                    userDataList += `
                        <tr>
                            <td>${log._source.logmessage}</td>
                            <td>${log._source.message.split("INFO:")[1]}</td>
                        </tr>
                        `
                });
                }
                else {
                    userDataList = `<tr><th colspan="4" style="text-align:center;"><u>U S E R  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                        </tr>`

                    data.hits.hits.forEach(user => {

                        userDataList += `
                            <tr>
                                <td>${user._source.name}</td>
                                <td>${user._source.email}</td>
                            </tr>
                            `
                    });
                }
                $("#logDataReciever").html(userDataList);
            }
            else {
                alert("No result found");
                $("#logDataReciever").html("No result found");
            }

            return;
        },
        error: function (xmlhttprequest, textstatus, message) {
            alert("error : " + message)
        }
    });



}

//===========================================================================================
//                G E T   L O G    D A T A
//===========================================================================================

//__________________________________________________________________________________________
//------------------------------------------------------------------------------------------
//      E N D    O F    J 2 E E     R E S T    S E R V I C E 
//__________________________________________________________________________________________
//------------------------------------------------------------------------------------------

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//      D I R E C T    S E A R C H    W I T H   E L K  -  S T A C K  (http://localhost:9200/)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//===========================================================================================
//               S E N D   L O G    D A T A
//===========================================================================================

// function sendLogData() {

//     $('.hidden').removeClass("active");
//     $('#sendLogData').addClass("active");
// }
// function sendLogDataService() {

//     //Getting form values
//     var name = $('#logDataName').val();
//     var email = $('#logDataEmail').val();

//     //Creating object
//     var data = {};

//     //Set values
//     data.name = name;
//     data.email = email;


//     //Stringify Data
//     data = JSON.stringify(data);

//     //Ajax Call
//     $.ajax({
//         url: "http://localhost:9200/logger/user",
//         data: data,
//         type: "post",
//         contentType: 'application/json',
//         dataType: "json",
//         timeout: 18000,
//         success: function (data) {
//             if (data) {

//                 console.log(data)

//                 if (data.result == "created") {
//                     alert("Data Log is created successfully");
//                 }
//             }
//             else {
//                 alert("Error occured , please try again later");
//             }
//         },
//         error: function (xmlhttprequest, textstatus, message) {
//             alert("error : " + message)
//         }
//     });

// }



// //===========================================================================================
// //                S E N D   L O G    D A T A
// //===========================================================================================


// //===========================================================================================
// //               G E T   L O G    D A T A
// //===========================================================================================

// var indices=[];
// var keys=null;
// function recieveLogData() {

//     $('.hidden').removeClass("active");
//     $('#getLogData').addClass("active");

//      //Ajax Call
//      $.ajax({
//         url: "http://localhost:9200/_cat/indices",
//         data: "",
//         //data:"",
//         type: "get",
//         timeout: 18000,
//         success: function (data) {

//             var atpos=null;
//             var index=null;
//             var lines=[];

//             lines=data.split("\n");

//             lines.forEach(line => {
                
//                 atpos=-1;
//                 atpos=line.indexOf("yellow");

//                 if(atpos==0){
//                     //get index
//                     index=line.substring(12).split(" ")[0];
                    
//                     console.log(atpos,index);
//                     //push index to indices array
//                     indices.push(index);
//                 }
//             }); 
//         }
//     });
    
// }

// function fetchSearchElement(){
//     var indexName = $('#getDataIndexName').val();
//     keys=null;
//     if(indices.includes(indexName)){

//         url = "http://localhost:9200/" + indexName + "/_search";

//         //Ajax Call
//         $.ajax({
//             url: url,
//             data: "",
//             type: "post",
//             contentType: 'application/json',
//             dataType: "json",
//             timeout: 18000,
//             success: function (data) {

//                 console.log(data.hits.hits[0]._source)
//                 //if there is any response data
//                 if (data.hits.hits.length > 0) {
//                     //Getting keys from Object (_source) to Array (keys)
//                     keys=Object.keys(data.hits.hits[0]._source);
                    
//                     userDataList = `<tr><th colspan="4" style="text-align:center;"><u>L O G  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
//                         <tr>
//                             <th>`+keys[0]+`</th>
//                             <th>`+keys[1]+`</th>
//                         </tr>`
    
//                     data.hits.hits.forEach(log => {
//                         //console.log(keys)
//                         userDataList += `
//                             <tr>
//                                 <td>${log._source[keys[0]]}</td>
//                                 <td>${log._source[keys[1]]}</td>
//                             </tr>
//                             `
//                     });

//                     var searchFields=`
//                         <div class="input-group mb-3">
//                             <span class="input-group-text" id="basic-addon1">@</span>
//                             <input type="text" id="get`+keys[1]+`" autocomplete="false" class="form-control"
//                                 placeholder="`+keys[1]+`" aria-label="get`+keys[1]+`" aria-describedby="basic-addon1">
//                         </div>`;
                    
//                     $("#searchFields").html(searchFields);
//                     $("#logDataReciever").html(userDataList);
//                 }
//             }
//         })
//     }
//     else{
//         $('#searchFields').html("");
//     }
// }

// function recieveLogDataService() {
//     console.log(indices + " "+keys);
//     //Getting form values
//     var indexName = $('#getDataIndexName').val();
    
//     var from = $('#getDataFrom').val();
//     var size = $('#getDataSize').val();
//     var url = null;
//     var indexName = $('#getDataIndexName').val();

//     if(indices.includes(indexName)){
        
//         var key1=$('#get'+keys[1]+'').val();
//     }

//     if (from === "" || !Number.isInteger(from)) {
//         from = 0;
//     }
//     if (size === "" || !Number.isInteger(size)) {
//         url = "http://localhost:9200/" + indexName + "/_search?from=" + from + "";
//     }
//     else {
//         url = "http://localhost:9200/" + indexName + "/_search?from=" + from + "&size=" + size + "";
//     }

//     //Creating object
//     var data = {};
//     var query = {};
//     var match_phrase = {};
//     //Set values
//     if(key1&&key1!=""){
//         console.log("case 1");
//         match_phrase[keys[1]]=(keys[1]=="logmessage")?key1+"\r" : key1;
//         query.match_phrase = match_phrase;
//         console.log(match_phrase)
//     }
//     else{
//         console.log("case 2");
//         query.match_all={}
//     }

    
//     data.query = query;

//     console.log(data)
//     //Stringify Data
//     data = JSON.stringify(data);
    

//     //Ajax Call
//     $.ajax({
//         url: url,
//         data: data,
//         //data:"",
//         type: "post",
//         contentType: 'application/json',
//         dataType: "json",
//         timeout: 18000,
//         success: function (data) {

//             console.log(data)
//             //Table data
//             var userDataList = null;
//             //If any data present
//             if (data.hits.hits.length > 0) {
//                 if (data.hits.hits[0]._source.logmessage) {

//                     userDataList = `<tr><th colspan="4" style="text-align:center;"><u>L O G  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
//                     <tr>
//                         <th>Log message</th>
//                         <th>Response</th>
//                     </tr>`

//                 data.hits.hits.forEach(log => {

//                     userDataList += `
//                         <tr>
//                             <td>${log._source.logmessage}</td>
//                             <td>${log._source.message.split("INFO:")[1]}</td>
//                         </tr>
//                         `
//                 });
//                 }
//                 else {
//                     userDataList = `<tr><th colspan="4" style="text-align:center;"><u>U S E R  &nbsp;  D E T A I L S  &nbsp;  L I S T</u></th></tr>
//                         <tr>
//                             <th>Name</th>
//                             <th>Email</th>
//                         </tr>`

//                     data.hits.hits.forEach(user => {

//                         userDataList += `
//                             <tr>
//                                 <td>${user._source.name}</td>
//                                 <td>${user._source.email}</td>
//                             </tr>
//                             `
//                     });
//                 }
//                 $("#logDataReciever").html(userDataList);
//             }
//             else {
//                 alert("No result found");
//                 $("#logDataReciever").html("No result found");
//             }

//             return;
//         },
//         error: function (xmlhttprequest, textstatus, message) {
//             alert("error : " + message)
//         }
//     });



// }

//===========================================================================================
//                G E T   L O G    D A T A
//===========================================================================================