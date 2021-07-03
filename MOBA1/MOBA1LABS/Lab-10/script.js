if (window.DeviceOrientationEvent) {
    var def = null;
    window.addEventListener('deviceorientation', function (e) {
        const a = Math.floor(e.alpha);
        if(def == null) {
            def = a - 90;
        }
        window.requestAnimationFrame(function () {
            document.getElementById("arrow").style.transform
                = 'rotate(' + (a - def) + 'deg)';
        })
    }, true);
}