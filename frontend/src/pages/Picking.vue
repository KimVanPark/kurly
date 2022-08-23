<template>
  <v-container fluid class="fill-height px-5">
    <v-row class="section overflow-y-auto">
      <v-col>
        <v-row class="mb-5">
          <v-col class="py-0">
            <span>
              <span class="info-text">{{ totalItemCount }}</span>
              개 중에
              <span class="info-text red--text">{{ remainItemCount }}</span>
              번째 피킹이에요!
            </span>
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
                v-model="currentItem.sku.name"
                background-color="grey lighten-2"
                readonly
                dense
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
                v-model="currentItem.location.code"
                background-color="grey lighten-2"
                readonly
                dense
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
                v-model="currentItem.totalCount"
                background-color="grey lighten-2"
                readonly
                dense
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
                v-model="remainQuantity"
                background-color="grey lighten-2"
                readonly
                dense
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
                ref="locationInput"
                v-model="postData.location"
                @keydown.enter.stop="onEnterLocation"
                solo
                dense
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
                ref="barcodeInput"
                v-model="postData.barcode"
                @keydown.enter.stop="onEnterBarcode"
                solo
                dense
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
                ref="pickingQuantityInput"
                v-model="postData.pickingQuantity"
                solo
                :background-color="pickingQuantityColor"
                :readonly="!isHighWorker"
                dense
            ></v-text-field>
          </v-col>
          <v-col class="one-line-text">
            <span>
              TOTE
            </span>
          </v-col>
          <v-col class=" multi-line-input">
            <v-text-field
                ref="toteInput"
                v-model="postData.tote"
                solo
                dense
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
    <v-snackbar
        v-model="snackbar.enable"
        timeout="1000"
        color="red accent-2"
    >
      {{ snackbar.text }}
    </v-snackbar>
    <v-row justify="center">
      <v-dialog
          v-model="dialog"
          persistent
          max-width="290"
      >
        <v-card>
          <v-card-title class="text-h5">
            피킹이 완료되었습니다!
          </v-card-title>
          <v-card-text>로그인 화면으로 돌아가 <br/>다음 피킹을 진행해주세요</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="#92A2EA"
                text
                @click="onCloseComplete"
            >
              로그인화면으로 돌아가기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <v-row justify="center">
      <v-dialog
          v-model="confirmDialog"
          persistent
          max-width="290"
      >
        <v-card>
          <v-card-title class="text-h5">
            피킹 확인
          </v-card-title>
          <v-card-text>
            <span class="font-weight-bold black--text">{{ postData.location }}</span>에서
            <span class="font-weight-bold black--text">{{ postData.tote }}</span>에
            <span class="font-weight-bold black--text">{{ currentItem.skuName }}</span>을
            <span class="font-weight-bold black--text">{{ postData.pickingQuantity }}</span>개
            피킹하시겠습니까?
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="red darken-1"
                text
                @click="confirmDialog = false"
            >
              취소
            </v-btn>
            <v-btn
                color="#92A2EA"
                text
                @click="doCloseConfirm"
            >
              피킹
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-container>
</template>

<script>
import pickingOrder from "@/api/pickingOrder";
import {mapGetters} from "vuex";
import {WORKER_STATUS} from "@/constant";

export default {
  name: "Picking",
  props: {
    active: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      uuid: "hi",
      postData: {
        location: '',
        barcode: '',
        pickingQuantity: null,
        tote: ''
      },
      pickingOrder: {
        id: '',
        items: []
      },
      currentItem: {
        id: '',
        location: {},
        sku: {},
        totalCount: 0,
        pickingCount: 0
      },
      snackbar: {
        enable: false,
        text: '로그인에 실패하였습니다. 작업자를 확인해주세요.'
      },
      dialog: false,
      confirmDialog: false
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
    },
    totalItemCount() {
      return this.pickingOrder.items.length
    },
    remainItemCount() {
      if (this.pickingComplete) {
        return this.totalItemCount
      }
      return (this.totalItemCount -
          this.pickingOrder.items.filter(item => item.totalCount > item.pickingCount).length) + 1
    },
    remainQuantity() {
      return this.currentItem.totalCount - this.currentItem.pickingCount
    },
    pickingComplete() {
      return this.pickingOrder.items.filter(item => item.totalCount > item.pickingCount).length === 0
    },
    isInValidLocation() {
      return this.postData.location !== this.currentItem.location.code
    },
    isInValidBarcode() {
      return this.postData.barcode !== this.currentItem.sku.barcode
    }
  },
  methods: {
    selectLocation() {
      this.$refs.locationInput.$el.getElementsByTagName('input')[0].select()
    },
    selectBarcode() {
      this.$refs.barcodeInput.$el.getElementsByTagName('input')[0].select()
    },
    selectPickingQuantity() {
      this.$refs.pickingQuantityInput.$el.getElementsByTagName('input')[0].select()
    },
    selectTote() {
      this.$refs.toteInput.$el.getElementsByTagName('input')[0].select()
    },
    onEnterLocation(event) {
      if (event.isComposing) {
        return;
      }
      if (this.isInValidLocation) {
        this.snackbar.text = '피킹 위치를 다시 한번 확인해주세요.'
        this.snackbar.enable = true
        this.selectLocation()
        return;
      }
      this.$refs.barcodeInput.focus()
    },
    onEnterBarcode(event) {
      if (event.isComposing) {
        return;
      }
      if (this.isInValidBarcode) {
        this.snackbar.text = '바코드가 올바른지 확인해주세요.'
        this.snackbar.enable = true
        this.selectBarcode()
        return;
      }
      if (!this.postData.pickingQuantity) {
        this.postData.pickingQuantity = 0
      }
      if (this.postData.pickingQuantity >= this.remainQuantity) {
        this.snackbar.text = '더 이상 피킹이 불가합니다.'
        this.snackbar.enable = true
        this.selectBarcode()
        return;
      }

      if (this.isHighWorker && this.active) {
        this.$nextTick(() => {
          this.selectPickingQuantity()
        })
        return;
      }

      this.postData.pickingQuantity++;
      this.selectBarcode()
    },
    async onClickButton() {
      if (this.isInValidLocation) {
        this.snackbar.text = '피킹 위치를 다시 한번 확인해주세요.'
        this.snackbar.enable = true
        this.selectLocation()
        return;
      }
      if (this.isInValidBarcode) {
        this.snackbar.text = '바코드가 올바른지 확인해주세요.'
        this.snackbar.enable = true
        this.selectBarcode()
        return;
      }
      if (!this.postData.pickingQuantity) {
        this.snackbar.text = '피킹 수량은 0보다 커야합니다.'
        this.snackbar.enable = true
        this.selectBarcode()
        return;
      }
      if (!this.postData.tote) {
        this.snackbar.text = '토트(바구니)를 스캔해주세요'
        this.snackbar.enable = true
        this.selectTote()
        return;
      }

      if (this.isHighWorker) {
        await this.postPicking()
        return;
      }
      this.confirmDialog = true
    },
    async doCloseConfirm() {
      this.confirmDialog = false
      await this.postPicking()
    },
    async postPicking() {
      const data = {
        workerId: this.getWorker.id,
        locationCode: this.postData.location,
        skuBarcode: this.postData.barcode,
        count: this.postData.pickingQuantity,
        toteCode: this.postData.tote
      }
      try {
        const item = await pickingOrder.postPicking(this.pickingOrder.id, this.currentItem.id, data)
        const itemIndex =
            this.pickingOrder.items.findIndex(pickingItem => pickingItem.id === item.id)
        this.pickingOrder.items[itemIndex] = item
        this.pickingOrder = JSON.parse(JSON.stringify(this.pickingOrder));
        this.currentItem = item
        this.selectItem()
      } catch (e) {
        console.log(e)
      }
    },
    selectItem() {
      if (this.currentItem.totalCount > this.currentItem.pickingCount) {
        this.resetSameItem()
        return;
      }
      if (this.pickingComplete) {
        this.dialog = true
        return;
      }
      this.currentItem = this.pickingOrder.items.find(item => item.totalCount > item.pickingCount)
      this.resetNewItem()
    },
    resetSameItem() {
      if (this.isHighWorker) {
        this.postData.pickingQuantity = null
        this.postData.tote = ''
        this.selectBarcode()
        return
      }
      this.postData.location = ''
      this.postData.barcode = ''
      this.postData.pickingQuantity = null
      this.postData.tote = ''
      this.selectLocation()
    },
    resetNewItem() {
      this.postData.location = ''
      this.postData.barcode = ''
      this.postData.pickingQuantity = null
      this.postData.tote = ''
      this.selectLocation()
    },
    onCloseComplete() {
      this.dialog = false
      this.$router.push("/")
    },
    async fetchPickingOrder() {
      const formData = {
        workerId: this.getWorker.id
      }
      try {
        const data = await pickingOrder.assignmentPickingOrder(formData)
        this.pickingOrder = data
        this.currentItem = data.items.find(item => item.totalCount > item.pickingCount)
        if (!this.currentItem) {
          await this.$router.push("/")
        }
      } catch (e) {
        console.error("error!")
      }
    }
  },
  async mounted() {
    if (!this.getWorker.id) {
      await this.$router.push("/")
      return
    }

    await this.fetchPickingOrder()
    if (this.currentItem) {
      this.$refs.locationInput.focus();
    }
  }
}
</script>

<style scoped lang="scss">
.section {
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

.start-picking-button {
  color: white;
  font-size: 16px;
  font-weight: bold;
}

.info-text {
  font-size: 24px;
  font-weight: bold;
}
</style>
