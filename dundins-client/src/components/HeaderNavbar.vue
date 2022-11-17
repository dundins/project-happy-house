<template>
  <v-app-bar color="white" flat>
    <v-container class="py-0 fill-height">
      <v-avatar class="mr-10" color="grey darken-1" size="32"></v-avatar>

      <router-link to="/" tag="v-btn">
        <v-btn rounded>Main</v-btn>
      </router-link>
      <router-link to="/about" tag="v-btn">
        <v-btn rounded>About</v-btn>
      </router-link>
      <router-link to="/apt" tag="v-btn">
        <v-btn rounded>Apt</v-btn>
      </router-link>

      <v-spacer></v-spacer>

      <v-responsive max-width="260">
        <v-text-field
          dense
          flat
          hide-details
          rounded
          solo-inverted
        ></v-text-field>
      </v-responsive>
    </v-container>
  </v-app-bar>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>
