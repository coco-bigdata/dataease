<template>
  <div>
    <div style="width: 100%">
      <el-popover
        v-model="isSetting"
        placement="right"
        width="400"
        trigger="click"
      >
        <el-col>
          <el-form ref="axisForm" :model="axisForm" label-width="80px" size="mini">
            <!--            <el-form-item :label="$t('chart.show')" class="form-item">-->
            <!--              <el-checkbox v-model="axisForm.show" @change="changeXAxisStyle">{{ $t('chart.show') }}</el-checkbox>-->
            <!--            </el-form-item>-->
            <el-form-item :label="$t('chart.position')" class="form-item">
              <el-radio-group v-model="axisForm.position" size="mini" @change="changeXAxisStyle">
                <el-radio-button label="top">{{ $t('chart.text_pos_top') }}</el-radio-button>
                <el-radio-button label="bottom">{{ $t('chart.text_pos_bottom') }}</el-radio-button>
              </el-radio-group>
            </el-form-item>
            <el-form-item :label="$t('chart.name')" class="form-item">
              <el-input v-model="axisForm.name" size="mini" @blur="changeXAxisStyle" />
            </el-form-item>
            <el-form-item :label="$t('chart.rotate')" class="form-item form-item-slider">
              <el-slider v-model="axisForm.axisLabel.rotate" show-input :show-input-controls="false" :min="-90" :max="90" input-size="mini" @change="changeXAxisStyle" />
            </el-form-item>
            <el-form-item :label="$t('chart.content_formatter')" class="form-item">
              <el-input v-model="axisForm.axisLabel.formatter" type="textarea" :autosize="{ minRows: 4, maxRows: 4}" @blur="changeXAxisStyle" />
            </el-form-item>
          </el-form>
        </el-col>

        <el-button slot="reference" size="mini" class="shape-item" :disabled="!axisForm.show">
          {{ $t('chart.xAxis') }}<i class="el-icon-setting el-icon--right" />
          <el-switch
            v-model="axisForm.show"
            class="switch-style"
            @click.stop.native
            @change="changeXAxisStyle"
          />
        </el-button>
      </el-popover>
    </div>
  </div>
</template>

<script>
import { DEFAULT_XAXIS_STYLE } from '../../chart/chart'

export default {
  name: 'XAxisSelector',
  props: {
    chart: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      axisForm: JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE)),
      isSetting: false
    }
  },
  watch: {
    'chart': {
      handler: function() {
        const chart = JSON.parse(JSON.stringify(this.chart))
        if (chart.customStyle) {
          let customStyle = null
          if (Object.prototype.toString.call(chart.customStyle) === '[object Object]') {
            customStyle = JSON.parse(JSON.stringify(chart.customStyle))
          } else {
            customStyle = JSON.parse(chart.customStyle)
          }
          if (customStyle.xAxis) {
            this.axisForm = customStyle.xAxis
          }
        }
      }
    }
  },
  mounted() {
  },
  methods: {
    changeXAxisStyle() {
      if (!this.axisForm.show) {
        this.isSetting = false
      }
      this.$emit('onChangeXAxisForm', this.axisForm)
    }
  }
}
</script>

<style scoped>
.shape-item{
  padding: 6px;
  border: none;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.form-item-slider>>>.el-form-item__label{
  font-size: 12px;
  line-height: 38px;
}
.form-item>>>.el-form-item__label{
  font-size: 12px;
}
.el-select-dropdown__item{
  padding: 0 20px;
}
  span{
    font-size: 12px
  }
  .el-form-item{
    margin-bottom: 6px;
  }

.switch-style{
  position: absolute;
  right: 10px;
  margin-top: -4px;
}
</style>
