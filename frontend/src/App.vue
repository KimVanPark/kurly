<template>
  <v-app>
    <v-app-bar class="kvp-app-bar"app color="#92A2EA" v-if="isNotLogin">
      <v-row>
        <v-col>
          <span class="title">
           {{ title }}
          </span>
        </v-col>
      </v-row>
      <v-switch
          class="switch-button"
          v-model="active"
          color="success"
          hide-details
          v-if="isHighWorker"
      ></v-switch>
    </v-app-bar>
    <v-main>
      <router-view :active="active"/>
    </v-main>
  </v-app>
</template>

<script>
import {mapGetters} from "vuex";
import {WORKER_STATUS} from "@/constant";

export default {
  data() {
    return {
      active: false
    }
  },
  computed: {
    ...mapGetters(['getWorker']),
    isHighWorker() {
      return this.getWorker.level === WORKER_STATUS.HIGH
    },
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
  max-width: 500px;
  margin:auto;
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

.v-application--wrap {
  min-height: 750px;
  max-height: 1000px;
}

.v-main {
  padding-top: 32px !important;
}

.kvp-app-bar {
  position: static !important;
  flex: none;
}

.title {
  color: white;
  font-size: 32px;
  font-weight: bold;
}

.switch-button {
  position: absolute;
  left: 80%;
}
</style>
