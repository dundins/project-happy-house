<template>
  <div class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom">
    <div class="container">
      <div class="left d-flex">
        <button
          class="navbar-toggler color-900"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbar-collapse"
          aria-controls="navbar-collapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <i class="fa fa-bars"></i>
        </button>
        <router-link class="navbar-brand" to="/home">
          <img style="width: 64px" src="../assets/img/ssafy-logo.png" />
        </router-link>
      </div>
      <div
        class="collapse navbar-collapse justify-content-between"
        id="navbar-collapse"
      >
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/board">공지사항</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/">실거래가</router-link>
          </li>
          <li class="ms-lg-2 ms-md-1 nav-item">
            <router-link class="nav-link" to="/house">매물</router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderNavbar",
  data() {
    return {};
  },
  computed: {},
  methods: {},
};
</script>

<style scoped>
.link {
  text-decoration: none;
}

#header {
  background-color: #fafaf8;
  height: 80px;
  box-shadow: 0px 0.1px 1px 1px #deddd6;
}
</style>
