import React from 'react';

import { Fullpage, Slide } from 'fullpage-react';

import AccountContainer from './containers/AccountContainer';

const slides = [
  <Slide><AccountContainer /></Slide>,
  <Slide>Slide 2</Slide>,
  <Slide>Slide 3</Slide>,
];

export default function AppLayout() {
  return (
    <Fullpage
      scrollSensitivity={7}
      touchSensitivity={7}
      scrollSpeed={300}
      slides={slides}
      hideScrollBars
      enableArrowKeys
    />
  );
}
