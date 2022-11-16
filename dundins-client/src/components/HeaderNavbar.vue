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

  <!-- <v-col style="display: flex" class="d-flex" lg="10">
    <v-select :items="items" label="시 선택" dense></v-select>
    <v-select :items="items" label="구 선택" dense></v-select>
    <v-select :items="items" label="동 선택" dense></v-select>
  </v-col> -->
  <v-container class="grey lighten-5">
    <v-row class="mb-6" no-gutters>
      <v-col md="6" offset-md="3">
        <v-card class="pa-2" outlined tile> .col-md-3 .offset-md-3 </v-card>
      </v-col>
      <v-col md="6" offset-md="3">
        <v-card class="pa-2" outlined tile> .col-md-3 .offset-md-3 </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>
