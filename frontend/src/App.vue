<template>
  <v-app>
    <v-app-bar app color="#eeeeee" v-if="isNotLogin">
      {{ title }}
    </v-app-bar>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  computed: {
    title() {
      return this.$route.name;
    },
    isNotLogin() {
      console.log("location is ", this.$route.path);
      return this.$route.path !== "/"
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
