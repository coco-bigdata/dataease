<template>
  <de-container v-loading="$store.getters.loadingMap[$store.getters.currentPath]" style="background-color: #f7f8fa">
    <de-main-container>
      <panel-main v-show="componentName==='PanelMain'" />
      <chart-edit v-if="componentName==='ChartEdit'" :param="param" />
      <panel-edit v-if="componentName==='PanelEdit'" />
      <!--      <component :is="component" :param="param" />-->
    </de-main-container>
  </de-container>
</template>

<script>
import bus from '@/utils/bus'
import DeMainContainer from '@/components/dataease/DeMainContainer'
import DeContainer from '@/components/dataease/DeContainer'
import PanelMain from '@/views/panel/list/PanelMain'
import ChartEdit from '@/views/chart/view/ChartEdit'
import PanelEdit from '@/views/panel/edit'

export default {
  name: 'Panel',
  components: { DeMainContainer, DeContainer, PanelMain, ChartEdit, PanelEdit },
  data() {
    return {
      component: PanelMain,
      componentName: 'PanelMain',
      param: {}
    }
  },
  mounted() {
    bus.$on('PanelSwitchComponent', (c) => {
      this.param = c.param
      this.componentName = c.name
      this.$store.dispatch('panel/setMainActiveName', c.name)
      // switch (c.name) {
      //   case 'PanelEdit':
      //     this.component = PanelEdit
      //     this.componentName = 'PanelEdit'
      //     break
      //   case 'ChartEdit':
      //     this.component = ChartEdit
      //     this.componentName = 'ChartEdit'
      //     break
      //   default:
      //     this.component = PanelMain
      //     this.componentName = 'PanelMain'
      //     break
      // }
    })
  },
  methods: {

  }
}
</script>

<style scoped>
  .ms-aside-container {
    height: calc(100vh - 56px);
    padding: 0px;
    min-width: 260px;
    max-width: 460px;
  }

  .ms-main-container {
    height: calc(100vh - 56px);
    padding: 0;
  }

</style>
