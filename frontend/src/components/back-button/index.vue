<template>
  <el-icon name="back" class="back-button" @click.native="jump" />
</template>

<script>
export default {
  name: 'BackButton',
  props: {
    // eslint-disable-next-line vue/require-default-prop
    path: String,
    // eslint-disable-next-line vue/require-default-prop
    name: String,
    // eslint-disable-next-line vue/require-default-prop
    to: Object
  },
  methods: {
    jump() {
      const { path, name, to } = this
      if (path) {
        this.$router.push(path)
      }
      if (name) {
        this.$router.push({ name: this.name })
      }
      if (to) {
        this.$router.push(to)
      }
    }
  }
}
</script>

<style lang="scss">
@import "~@/styles/mixin.scss";
@import "~@/styles/variables.scss";

.back-button {
  cursor: pointer;
  margin-right: 10px;
  font-weight: 600;

  &:active {
    transform: scale(0.85);
  }
}
</style>
