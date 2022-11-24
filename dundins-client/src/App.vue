<template>
  <div id="app">
    <router-view id="rv" />
    <Footer v-if="$route.name !== 'apt'" class="mt-5"></Footer>
  </div>
</template>

<script>
import Footer from "./components/layout/Footer.vue";

export default {
  name: "App",
  components: {
    Footer,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#rv {
  min-height: 1000px;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap");
* {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
