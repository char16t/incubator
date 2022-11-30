// See https://docusaurus.io/docs/site-config for all the possible
// site configuration options.

const repoUrl = "https://github.com/char16t/multimodulescala";
const baseUrl = '/'
const siteConfig = {
  title: 'Multimodulescala', 
  url: 'https://multimodulescala.manenkov.com',
  tagline: 'Multimodulescala — project template',
  projectName: 'multimodulescala-docs',
  organizationName: 'char16t',
  headerLinks: [
    { href: `${baseUrl}api/index.html`, label: 'API'},
  ],
  separateCss: ["api"],
  headerIcon: '',
  favicon: '',
  copyright: `Copyright © Multimodulescala ${new Date().getFullYear()}`,
  baseUrl: baseUrl,
  colors: {
    primaryColor: '#004d4d',
    secondaryColor: '#00a1a',
  },
  repoUrl
};

module.exports = siteConfig;