import React from 'react';

import { Fullpage, Slide } from 'fullpage-react';

import AccountSlider from './sliders/AccountSlider';

const slides = [
  <Slide><AccountSlider /></Slide>,
  <Slide> Slide 2 </Slide>,
  <Slide> Slide 3 </Slide>,
];

const fullPageOptions = {
  scrollSensitivity: 7,
  touchSensitivity: 7,
  scrollSpeed: 400,
};

export default function SlidersLayout() {
  const {
    scrollSensitivity,
    touchSensitivity,
    scrollSpeed,
  } = fullPageOptions;

  return (
    <Fullpage
      scrollSensitivity={scrollSensitivity}
      touchSensitivity={touchSensitivity}
      scrollSpeed={scrollSpeed}
      slides={slides}
      hideScrollBars
      enableArrowKeys
    />
  );
}
