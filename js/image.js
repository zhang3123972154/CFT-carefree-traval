
export function pathToBase64(path) {
    return new Promise((resolve, reject) => {
        plus.io.resolveLocalFileSystemURL(path, function(entry) {
            entry.file(function(file) {
                var fileReader = new plus.io.FileReader();
                fileReader.readAsDataURL(file);
                fileReader.onloadend = function(evt) {
                    // base64字符串
                    resolve(evt.target.result);
                };
                fileReader.onerror = function(err) {
                    reject(err);
                };
            }, function(e) {
                reject(e);
            });
        }, function(e) {
            reject(e);
        });
    });
}