<template>
  <v-container fluid class="fill-height px-5" >
    <v-row class="section overflow-y-auto">
      <v-col>
        <v-row>
          <v-col class="py-0">
            10개 중에 1번째 피킹이에요!
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              제품명
            </span>
          </v-col>
          <v-col class="pb-0 one-line-input">
            <v-text-field
                solo
                label="SKU"
                background-color="grey lighten-2"
                disabled
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              피킹 위치
            </span>
          </v-col>
          <v-col class="pb-0 one-line-input">
            <v-text-field
                solo
                label="Location"
                background-color="grey lighten-2"
                disabled
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              전체 수량
            </span>
          </v-col>
          <v-col class="pb-0 multi-line-input">
            <v-text-field
                solo
                label="50"
                background-color="grey lighten-2"
                disabled
            ></v-text-field>
          </v-col>
          <v-col class="one-line-text">
            <span>
              잔여 수량
            </span>
          </v-col>
          <v-col class="pb-0 multi-line-input">
            <v-text-field
                solo
                label="50"
                background-color="grey lighten-2"
                disabled
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              피킹 위치
            </span>
          </v-col>
          <v-col class="pb-0 one-line-input" >
            <v-text-field
                v-model="postData.location"
                solo
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              바코드
            </span>
          </v-col>
          <v-col class="one-line-input">
            <v-text-field
                v-model="postData.barcode"
                solo
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="align-center">
          <v-col class="one-line-text">
            <span>
              피킹 수량
            </span>
          </v-col>
          <v-col class="multi-line-input">
            <v-text-field
                v-model="postData.pickingQuantity"
                solo
                label="50"
                :background-color="pickingQuantityColor"
                :disabled="!isHighWorker"
            ></v-text-field>
          </v-col>
          <v-col class="one-line-text">
            <span>
              TOTE
            </span>
          </v-col>
          <v-col class=" multi-line-input">
            <v-text-field
                v-model="postData.tote"
                solo
                label="50"
                background-color="grey lighten-2"
                disabled
            ></v-text-field>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-btn color="#92A2EA"
           @click="onClickButton" block
           height="50">
          <span class="start-picking-button">
            피킹
          </span>
    </v-btn>
  </v-container>
</template>

<script>
import api from "@/api";
import {mapGetters} from "vuex";
import {WORKER_STATUS} from "@/constant";

export default {
  name: "Picking",
  data() {
    return {
      uuid: "hi",
      postData: {
        location: '',
        barcode: '',
        pickingQuantity: null,
        tote: ''
      }
    }
  },
  computed: {
    ...mapGetters(['getWorker']),
    isHighWorker() {
      return this.getWorker.level === WORKER_STATUS.HIGH
    },
    pickingQuantityColor() {
      if (!this.isHighWorker) {
        return 'grey lighten-2';
      }
      return null;
    }
  },
  methods: {
    async onClickButton() {
      try {
        const {data} = await api.request({
          methods: "get",
          url: "/test"
        })
        this.uuid = data;
      } catch (e) {
        console.error("error!!")
      }
    }
  },
  mounted() {
    console.log("worker is", this.getWorker)
  }
}
</script>

<style scoped>
.section{
  height: calc(100% - 100px);
}

.one-line-text {
  padding-bottom: 22px;
  flex-basis: 25%;
}
.one-line-input {
  flex-basis: 75%;
}

.multi-line-input {
  flex-basis: 25%;
  padding-bottom: 0px;
}

.start-picking-button{
  color: white;
  font-size: 16px;
  font-weight: bold;
}
</style>
