import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";
import IconsResolver from 'unplugin-icons/resolver'
import Icons from 'unplugin-icons/vite'

export default defineConfig({
  plugins: [
      vue(),
      AutoImport({
          /* Auto Import Functions From Vue, e.g. ref reactive, toRef */
          imports: ['vue'],

          resolvers: [
              ElementPlusResolver(),

              /* Auto Import icon Components */
              IconsResolver({
                  prefix: 'Icon',
              })
          ],

      }),
      Components({
          resolvers: [
              IconsResolver({
                  enabledCollections: ['el']
              }),

              ElementPlusResolver()
          ],
      }),
      Icons({
          autoInstall: true,
      }),
  ]
})
