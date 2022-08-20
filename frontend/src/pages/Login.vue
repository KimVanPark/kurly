<template>
  <v-container fluid class="fill-height">
    <h1 class="kvp-banner">
      KVP X Kurly
    </h1>
    <v-row class="mb-10">
      <v-col>
        <v-row class="flex-column">
          <v-col class="pa-0 pl-5 text-start">
              <span class="info-text">
                작업자 정보를 입력해주세요.
              </span>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pb-0">
              <v-text-field
                  ref="workerInput"
                  v-model="worker"
                  solo
                  label="작업자 입력"
              ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-btn color="#92A2EA"
                   @click="onClickButton" block>
              <span class="start-picking-button">
                피킹하러 가기
              </span>
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import {mapGetters} from "vuex";

export default {
  name: "Login",
  data() {
    return {
      worker: ""
    }
  },
  computed: {
    ...mapGetters(['getWorker'])
  },
  methods: {
    onClickButton() {
      console.log("start picking!", this.worker);
      this.$router.push("picking")
    }
  },
  mounted() {
    this.$refs.workerInput.focus();
    console.log("before worker is", this.getWorker);
    this.$store.commit("setWorker",{
      name: 'sunho',
      level: 'high'
    })
  }
}
</script>

<style scoped lang="scss">
.info-text {
  font-size: 16px;
  font-weight: bold;
}
.kvp-banner {
  position: absolute;
  top: 10px;
}

.start-picking-button {
  color: white;
  font-size: 16px;
  font-weight: bold;
}
</style>
