<template>
  <div>
    <b-navbar variant="info" class="bg-white">
      <router-link class="navbar-brand" to="/home">
        <img style="width: 64px" src="@/assets/img/dundins-logo.png" />
      </router-link>

      <b-navbar-nav>
        <router-link class="nav-link" to="/board">공지사항</router-link>
        <router-link class="nav-link" to="/house">매물</router-link>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <router-link class="nav-link" to="/user">마이 페이지</router-link>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
