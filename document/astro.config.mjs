import starlight from '@astrojs/starlight'
import { defineConfig } from 'astro/config'
import starlightTypeDoc, { typeDocSidebarGroup } from 'starlight-typedoc'
import remarkMermaid from 'remark-mermaidjs'

export default defineConfig({
	integrations: [
		starlight({
			plugins: [
				// Generate the documentation.
				starlightTypeDoc({
					entryPoints: ['../packages/core/src/index.ts'],
					tsconfig: '../packages/core/tsconfig.json',
				}),
			],
			sidebar: [
				{
					label: 'Guides',
					items: [
						{ label: 'Example Guide', link: '/guides/example/' },
						{ label: 'Ot', link: '/ot/ot/' }
					],
				},
				// Add the generated sidebar group to the sidebar.
				typeDocSidebarGroup,
			],
			title: 'My Docs',
		}),
	],
	markdown: {
		remarkPlugins: [
			remarkMermaid,
		],
	},
})
