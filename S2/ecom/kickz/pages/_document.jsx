import { Html, Head, Main, NextScript } from 'next/document'
import BackgroundFiller from './componements/BackgroundFiller'


export default function Document() {



  return (
    <Html lang="en">
      <Head />
      <body className='h-screen w-screen bg-[#ede9e6] overflow-x-hidden'>
        <BackgroundFiller />
        <Main/>
        <NextScript />
        <script src="./TW-ELEMENTS-PATH/dist/js/index.min.js"></script>
      </body>
    </Html>
  )
}
