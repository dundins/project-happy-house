<template>
  <div>
    <b-navbar style="padding: 0" variant="info" class="bg-white">
      <router-link class="navbar-brand" to="/home">
        <img style="width: 64px" src="@/assets/img/dundins-logo.png" />
      </router-link>
      <b-navbar-nav>
        <router-link class="nav-link" to="/house">부동산 매물</router-link>
        <li class="ms-lg-2 ms-md-1 nav-item nav-link">|</li>
        <router-link class="nav-link" to="/board">공지사항</router-link>
        <li class="ms-lg-2 ms-md-1 nav-item nav-link">|</li>
        <router-link class="nav-link" to="/account">마이 페이지</router-link>
      </b-navbar-nav>
    </b-navbar>
  </div>
</template>

<script>
export default {};
</script>

<style scoped></style>
