<template>
  <b-row>
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
  </b-row>
</template>

<script>
export default {};
</script>

<style></style>
