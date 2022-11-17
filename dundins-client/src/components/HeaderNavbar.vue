<template>
  <!-- <v-app-bar color="white" flat>
    <v-container class="py-0 fill-height">
      <router-link to="/" tag="v-btn">
        <v-btn>Main</v-btn>
      </router-link>
      <router-link to="/about" tag="v-btn">
        <v-btn>게시판</v-btn>
      </router-link>
      <router-link to="/apt" tag="v-btn">
        <v-btn>Apt</v-btn>
      </router-link>
    </v-container>
  </v-app-bar> -->

  <v-container data-app>
    <v-col></v-col>
    <v-row no-gutters>
      <v-col>
        <router-link style="text-decoration: none" to="/">
          <v-btn>
            <span class="material-icons"> home</span>
            메인 화면
          </v-btn>
        </router-link>
      </v-col>
      <v-col>
        <router-link style="text-decoration: none" to="/about">
          <v-btn>
            <span class="material-icons"> post_add</span>
            게시판
          </v-btn>
        </router-link>
      </v-col>
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
    <v-col></v-col>
    <v-divider></v-divider>

    <v-col></v-col>
    <v-row>
      <v-col>
        <span class="material-icons"> search </span>
        원하시는 지역을 선택해주세요.
      </v-col>
    </v-row>
    <v-row>
      <v-col style="display: flex" class="d-flex">
        <v-select :items="items" label="시 선택" dense></v-select>
        <v-select :items="items" label="구 선택" dense></v-select>
        <v-select :items="items" label="동 선택" dense></v-select>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <h2>hello</h2>
      </v-col>
      <v-col>
        <h2>hello</h2>
      </v-col>
    </v-row>
    <v-col>
      <h2>hello</h2>
    </v-col>
    <v-col>
      <h2>hello</h2>
    </v-col>

    <v-col>
      <h2>hello</h2>
    </v-col>
    <v-col>
      <h2>hello</h2>
    </v-col>

    <v-row>
      <h2>dd</h2>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      items: ["서울특별시", "강남구", "역삼동", "D", "E"],
    };
  },
};
</script>
