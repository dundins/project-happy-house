<template>
  <div>
    <section>
      <div class="bg"></div>
      <h1>Learn to code by cloning real services</h1>
      <p>
        Over 6,000+ students are cloning Instagram, Bitcoin, Netflix, Uber, and
        More!
      </p>
      <side-search-bar></side-search-bar>
    </section>
  </div>
</template>

<script>
import SideSearchBar from "@/components/sidebar/SideSearchBar.vue";

export default {
  data() {
    return {};
  },
  components: { SideSearchBar },
};
</script>

<style scoped></style>
