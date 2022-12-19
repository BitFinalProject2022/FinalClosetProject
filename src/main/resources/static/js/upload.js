function uploadData() {
  "use strict";
  $uploadForm = document.getElementById("uploadForm");

  //===== Prealoder
  $uploadForm.onclick = function () {
    window.setTimeout(fadeout, 200);
  };
  function fadeout() {
    document.querySelector(".preloader").style.opacity = "0";
    document.querySelector(".preloader").style.display = "none";
  }

  // WOW active
  new WOW().init();
}
