<template>
  <v-row no-gutters>
    <v-row>
      <v-col>
        <router-link style="text-decoration: none" to="/">
          <v-btn>
            <span class="material-icons"> home</span>
            메인 화면
          </v-btn>
        </router-link>
      </v-col>
      <v-col>
        <router-link style="text-decoration: none" to="/board">
          <v-btn>
            <span class="material-icons"> post_add</span>
            게시판
          </v-btn>
        </router-link>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <router-link style="text-decoration: none" to="/apt">
          <v-btn>
            <span class="material-icons"> room</span>
            관심 매물
          </v-btn>
        </router-link>
      </v-col>
      <v-col>
        <router-link style="text-decoration: none" to="/login">
          <v-btn>
            <span class="material-icons"> people</span>
            로그인
          </v-btn>
        </router-link>
      </v-col>
    </v-row>
  </v-row>
</template>

<script>
export default {};
</script>

<style></style>
