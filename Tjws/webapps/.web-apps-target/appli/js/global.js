// function load() {
//     $.ajax({
//         url: "http://localhost:8040/dir/dir",
//         type: "GET",
//         dataType: "text/json",
//         success: function (data) {
//             console.log(data);
//             $("#resultat").html("");
//             for (var i = 0; i < data.length; i++) {
//                 ligne = $("<a></a>").attr("path", data[i].path).attr("dir", data[i].dir).html(data[i].name);
//                 if (data[i].dir) {
//                     ligne.click(function () {
//                         load($(this).attr("path"));
//                     });
//                 }
//
//
//                 $("#resultat").append("<li></li>").append(ligne);
//             }
//         }
//     })
// }


icons = {
    "txt": "description",
    "jpg": "image",
    "png": "image",
    "gif": "image",
    "mp3": "audiotrack",
    "mp4": "movie",
    "pdf": "picture_as_pdf",
    "doc": "description",
    "docx": "description",
    "xls": "description",
    "xlsx": "description",
    "ppt": "description",
    "pptx": "description",
    "zip": "archive",
    "rar": "archive",
    "7z": "archive",
    "tar": "archive",
    "gz": "archive",
    "bz2": "archive",
    "iso": "archive",
    "exe": "insert_drive_file",
    "dll": "insert_drive_file",
    "msi": "insert_drive_file",
    "jar": "archive",
    "sh": "code",
    "py": "code",
    "php": "code",
    "html": "language",
    "css": "code",
    "js": "code",
    "json": "code",
    "xml": "code",
    "java": "code",
    "c": "code",
    "cpp": "code",
    "h": "code",
    "hpp": "code",
    "cs": "code",
    "cxx": "code",
    "mov": "movie",
    "avi": "movie",
    "mkv": "movie",
    "flv": "movie",
    "mpg": "movie",
    "mpeg": "movie",

}


function openFile(str, path) {
    $(".window").html("");
    $(".window").append("<span onClick=$('.window').removeClass('open') class='material-icons close'>close</span>");
    $(".window").append("<iframe class='file' src='/dir/file?file=" + path + "'></iframe>");

    $(".window").addClass("open");
}


function close() {
    console.log("close");
    $(".window").removeClass("open");
}

function loadPath(path) {
    $.ajax({
        url: "http://localhost:8040/dir/dir",
        type: "GET",
        dataType: "json", // Type de données de retour
        data: {
            "file": path
        },
        success: function (data) {
            console.log(data);
            $("#resultat").html("");

            console.log(data.length);

            for (var i = 0; i < data.length; i++) {
                ligne = $("<li></li>").attr("path", data[i].path).attr("dir", data[i].dir).attr("type", data[i].type);
                nom = $("<span class='nom'></span>").html(data[i].nom);
                icon = $("<span class='material-icons md-18'>description</span>");
                date = $("<span class='date'></span>").html(new Date(data[i].date).toLocaleString());
                taille = $("<span class='taille'></span>").html(nFormatter(parseInt(data[i].taille, 10), 2));
                if (data[i].dir) {
                    icon = $("<span class='material-icons md-18'>folder</span>");
                    ligne.click(function () {
                        loadPath($(this).attr("path"));
                    });
                } else if (!!data[i].type && data[i].nom != "..") {
                    ligne.click(function () {
                        openFile($(this).attr("type"), $(this).attr("path"));
                    });
                }


                data[i].nom.split(".").forEach(function (extension) {
                    if (icons[extension]) {
                        icon = $("<span class='material-icons md-18'>" + icons[extension] + "</span>");
                    }
                });

                ligne.append(icon);
                ligne.append(nom);
                if (data[i].nom != "..") {
                    ligne.append(date);
                    ligne.append(taille);
                }
                $("#resultat").append(ligne);
            }
        },
        error: function (data) {
            console.log(data);
        }
    })
};

$(document).ready(function () {
    loadPath("./");
});


function nFormatter(num, digits) {
    var si = [
        {value: 1, symbol: "o"},
        {value: 1E3, symbol: "ko"},
        {value: 1E6, symbol: "Mo"},
        {value: 1E9, symbol: "Go"},
    ];
    var rx = /\.0+$|(\.[0-9]*[1-9])0+$/;
    var i;
    for (i = si.length - 1; i > 0; i--) {
        if (num >= si[i].value) {
            break;
        }
    }
    return (num / si[i].value).toFixed(digits).replace(rx, "$1") + " " + si[i].symbol;
}
