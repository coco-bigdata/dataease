<template>
  <layout-content>
    <div style="width: 100%;display: flex;justify-content: center;">
      <el-card class="box-card about-card">
        <div class="form-header">
          <span>{{ $t('user.change_password') }}</span>
        </div>
        <el-form ref="createUserForm" :model="form" :rules="rule" size="small" label-width="auto" label-position="right">
          <el-form-item :label="$t('user.origin_passwd')" prop="oldPwd">
            <el-input v-model="form.oldPwd" type="password" />
          </el-form-item>
          <el-form-item :label="$t('user.new_passwd')" prop="newPwd">
            <el-input v-model="form.newPwd" type="password" />
          </el-form-item>
          <el-form-item :label="$t('user.confirm_passwd')" prop="repeatPwd">
            <el-input v-model="form.repeatPwd" type="password" />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="save">{{ $t('commons.confirm') }}</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </layout-content>
</template>

<script>
import LayoutContent from '@/components/business/LayoutContent'
import { updatePersonPwd } from '@/api/system/user'
export default {

  components: { LayoutContent },
  data() {
    return {
      form: {

      },
      rule: {

        oldPwd: [
          { required: true, message: this.$t('user.input_password'), trigger: 'blur' }
        ],
        newPwd: [
          { required: true, message: this.$t('user.input_password'), trigger: 'blur' },
          {
            required: true,
            pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,30}$/,
            message: this.$t('member.password_format_is_incorrect'),
            trigger: 'blur'
          }
        ],
        repeatPwd: [
          { required: true, message: this.$t('user.input_password'), trigger: 'blur' },
          { required: true, trigger: 'blur', validator: this.repeatValidator }
        ]

      }

    }
  },
  mounted() {
    this.$nextTick(() => {
      this.$store.dispatch('app/toggleSideBarHide', true)
    })
  },
  created() {
    this.$store.dispatch('app/toggleSideBarHide', true)
  },
  methods: {
    repeatValidator(rule, value, callback) {
      if (value !== this.form.newPwd) {
        callback(new Error(this.$t('member.inconsistent_passwords')))
      } else {
        callback()
      }
    },

    save() {
      this.$refs.createUserForm.validate(valid => {
        if (valid) {
          const param = {
            password: this.form.oldPwd,
            newPassword: this.form.newPwd
          }
          updatePersonPwd(param).then(res => {
            this.$success(this.$t('commons.save_success'))
            this.$router.push('/panel/index')
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.about-card {
  background: inherit;
  margin-top: 5%;
  flex-direction: row;
  width: 640px;
  min-width: 640px;
  height: auto;
  position: relative;
  >>>div.el-card__header {
    padding: 0;
  }
}
.form-header {
    line-height: 60px;
    font-size: 18px;
}
</style>
