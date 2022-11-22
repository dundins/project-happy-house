<template>
  <div id="homeview">
    <header-navbar></header-navbar>
    <section>
      <div class="bg"></div>
      <h1>Learn to code by cloning real services</h1>
      <p>
        Over 6,000+ students are cloning Instagram, Bitcoin, Netflix, Uber, and
        More!
      </p>
      <a href="#" class="btn">Start Coding Like a Boss</a>
    </section>
    <!-- <div>
      <b-card
        overlay
        img-src="https://cdn.pixabay.com/photo/2016/11/18/17/20/living-room-1835923_960_720.jpg"
        img-alt="Card Image"
        text-variant="white"
        title="Image Overlay"
        sub-title="Subtitle"
      >
        <b-card-text>
          Some quick example text to build on the card and make up the bulk of
          the card's content.
        </b-card-text>
      </b-card>
    </div> -->
  </div>
</template>

<script>
import HeaderNavbar from "@/components/HeaderNavbar";

export default {
  name: "HomeView",
  components: { HeaderNavbar },
};
</script>

<style>
.bg {
  background-color: rgba(0, 0, 0, 0.3); /*살짝 투명한 검정으로 배경색*/
  width: 100%;
  height: 500px;
  position: absolute; /*다른 요소들 위로 겹쳐질 수 있게함*/
  top: 0px;
}
section {
  background-image: url(https://cdn.pixabay.com/photo/2016/11/18/17/20/living-room-1835923_960_720.jpg);
  background-size: cover;
  display: block;
  width: 100%;
  height: 500px;
  color: #ffffff;
  text-align: center;
  padding-top: 150px;
  position: absolute; /*다른 요소들 위로 겹쳐질 수 있게함*/
}

section h1 {
  font-size: 2.5em;
}
section p {
  margin-bottom: 30px;
}

section h1,
p,
a {
  position: relative;
  z-index: 1000; /*맨 앞으로 나오도록 함*/
}
</style>
