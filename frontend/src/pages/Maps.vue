<template>
  <div id = "map">
  </div>
</template>
<script>
export default {
  mounted() {
    // 네이버 지도 API 로드
    const script = document.createElement("script");
    script.src =
      "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId="+process.env.VUE_APP_API_KEY;
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);

    script.onload = () => {
      // 네이버 지도 생성
      new window.naver.maps.Map("map", {
        center: new window.naver.maps.LatLng(37.5670135, 126.9783740),
        zoom: 10
      });
    };
  }
}
</script>
<style>
#map {
  min-height: calc(100vh - 123px);
}
</style>
