<template>
  <v-card class="overflow-hidden">
    <v-app-bar
      color="#0D47A1"
      dark
      shrink-on-scroll
      prominent
      src="https://cdn.pixabay.com/photo/2016/11/02/10/04/chicago-1791002_960_720.jpg"
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-3"
      height="40"
    >
      <v-app-bar-title>Title</v-app-bar-title>

      <template v-slot:extension>
        <v-tabs align-with-title>
          <v-tab>공지사항</v-tab>
          <v-tab>Tab 2</v-tab>
          <v-tab>Tab 3</v-tab>
        </v-tabs>
      </template>
    </v-app-bar>
    <v-app>
      <v-main>
        <v-sheet
          id="scrolling-techniques-3"
          class="overflow-y-auto"
          max-height="100%"
        >
          <v-container
            class="bv-example-row mt-3 text-center"
            style="height: 500px"
          >
            <h3 class="underline-hotpink">
              <v-icon icon="journals"></v-icon> 공지사항
            </h3>
            <router-view></router-view>
          </v-container>
        </v-sheet>
      </v-main>
    </v-app>
  </v-card>
</template>

<script>
export default {
  name: "AppBoard",
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
.v-app-bar-title > div {
  width: 100px;
}
</style>
