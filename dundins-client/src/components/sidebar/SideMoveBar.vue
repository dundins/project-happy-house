<template>
  <div>
    <b-nav>
      <b-nav-item active>
        <router-link style="text-decoration: none" to="/home">
          <img
            src="@/assets/img/ssafy-logo.png"
            style="width: 50px"
            alt="Kitten"
        /></router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link
          style="text-decoration: none"
          to="/board"
          :key="$route.fullPath"
        >
          공지 사항
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link style="text-decoration: none" to="/user">
          마이 페이지
        </router-link>
      </b-nav-item>
    </b-nav>
  </div>
  <!-- <b-row>
    <b-row>
      <b-col>
        <router-link style="text-decoration: none" to="/">
          <b-button>
            <span class="material-icons"> home</span>
            메인 화면
          </b-button>
        </router-link>
      </b-col>
      <b-col>
        <router-link style="text-decoration: none" to="/board">
          <b-button>
            <span class="material-icons"> post_add</span>
            게시판
          </b-button>
        </router-link>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <router-link style="text-decoration: none" to="/apt">
          <b-button>
            <span class="material-icons"> room</span>
            관심 매물
          </b-button>
        </router-link>
      </b-col>
      <b-col>
        <router-link style="text-decoration: none" to="/login">
          <b-button>
            <span class="material-icons"> people</span>
            로그인
          </b-button>
        </router-link>
      </b-col>
    </b-row>
  </b-row> -->
</template>

<script>
export default {};
</script>

<style></style>
