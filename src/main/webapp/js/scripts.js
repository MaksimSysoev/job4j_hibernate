var brands = [];
var ads = [];

function loadAds(arr) {
    if (arr.length>0) {
    $('#tbody').remove();
    $("#table").append(" <tbody id=\'tbody\'>");
    var num = 1;

    for (var i = 0; i < arr.length; i++) {
        $("#tbody").append("<tr>");
        $("#tbody").append("<td>" + num++ + "</td>");
        $("#tbody").append("<td>" + arr[i].name + "</td>");
        $("#tbody").append("<td>" + arr[i].brend + " / "+ arr[i].model +"</td>");
        $("#tbody").append("<td>"+arr[i].img+"</td>");
        if (ads[i].active=="true") {
            $("#tbody").append("<td>" +
                "<div class=\"custom-control custom-switch\">" +
                "<input type=\"checkbox\" onclick='activeChange("+arr[i].id+"); ' class=\"custom-control-input\" checked id=\"customSwitch"+arr[i].id+"\">" +
                "<label class=\"custom-control-label\" for=\"customSwitch"+arr[i].id+"\">Активно</label>" +
                "</div>" +
                "</td>");
        } else {
            $("#tbody").append("<td>" +
                "<div class=\"custom-control custom-switch\">" +
                "<input type=\"checkbox\" onclick='activeChange("+arr[i].id+"); ' class=\"custom-control-input\"  id=\"customSwitch"+arr[i].id+"\">" +
                "<label class=\"custom-control-label\" for=\"customSwitch"+arr[i].id+"\">Неактивно</label>" +
                "</div>" +
                "</td>");
        }

        $("#tbody").append("<td>" + arr[i].date + "</td>");
        $("#tbody").append("</tr>");
    }
    $("#table").append(" </tbody>");
    } else {
        alert("Нет объявлений по заданному критерию");
    }
}

function loadData() {
    $.ajax( {
        method: 'get',
        url:'./lk',
        complete: function(data) {
            brands = JSON.parse(data.responseText);
            ads = brands[3];
             console.log(JSON.parse(data.responseText));
            for (var i = 0; i < brands.length-1; i++) {
                $("#brand").append("<option value="+brands[i].id+" onclick='loadModelsByBrand(); '>"+brands[i].name+"</option>");
            }
            loadAds(ads);
            loadModels();
        }
    });
}

function activeChange(id) {
    var active;
    if ($("#customSwitch"+id+"").is(':checked')) {
        $("label[for='customSwitch"+id+"']").text("Активно");
        active = true;
    } else {
        $("label[for='customSwitch"+id+"']").text("Неактивно");
        active = false;
    }

    $.ajax ({
        method:'post',
        url:'./lk',
        data: {
            id: id,
            active: active
        },
        complete: function () {

        }
    });

}

function loadModelsByBrand() {
    var id = $("#brand option:selected").val()-1;
    var model = brands[id].model;
    $("#model").find('option:not(:first)').remove();

    for (var i = 0; i < model.length; i++) {
        $("#model").append("<option value="+model[i].id+">"+model[i].name+"</option>");

    }
}

function loadModels() {
    for (var i = 0; i < brands.length-1; i++) {
        $("#sortmodel").append("<option value="+brands[i].id+">"+brands[i].name+"</option>");
    }
}

function createAds() {
    $.ajax({
        method: 'post',
        url:'./lk',
        enctype: 'multipart/form-data',
        data: {
            brand: $("#brand").val(),
            model: $("#model").val(),
            body: $("#body").val(),
            ads: $("#ads").val(),
            file: $("#file").val()
        },
        complete: loadData()
    });
}

function formatDate(date) {

    var dd = date.getDate();
    if (dd < 10) dd = '0' + dd;

    var mm = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;

    var yy = date.getFullYear();

    return yy + '-' + mm + '-' + dd;
}

//Сортировка за последние сутки
function sortDay() {
    var d = formatDate(new Date());
    var adsByDay=[];
    var count = 0;
    for(var i = 0; i < ads.length; i++) {
        if (d==ads[i].date.substr(0,10)) {
            adsByDay[count] = ads[i];
            count++;
        }
    }

    loadAds(adsByDay);
}

// Сортировка по моделе
function sortModel() {
    var id = $("#sortmodel option:selected").val();
    var adsByCar=[];
    var count = 0;
    for(var i = 0; i < ads.length; i++) {

        if (id==ads[i].brandid) {
            adsByCar[count] = ads[i];
            count++;
        }
    }

    loadAds(adsByCar);
}

// Сортировка по фото
function sortImg() {
    var adsByImg=[];
    var count = 0;
    for(var i = 0; i < ads.length; i++) {

        if (ads[i].img!="no-photo") {
            adsByImg[count] = ads[i];
            count++;
        }
    }

    loadAds(adsByImg);
}

